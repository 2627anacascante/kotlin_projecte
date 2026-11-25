Video Aplicació, de la part 05 a la part 06-> link: https://drive.google.com/file/d/1y3pUUMdqpspY5izHf9X5cKEDOb_4YryB/view?usp=sharing

Documentació part 7.1

OrientaApp — ACT07.1 ViewModel i observabilitat

Actualitzacións: he refet Login i Registre amb MVVM i validem els camps, i he corregit deute tècnic anterior.

El patró MVVM a OrientaApp

Separem cada pantalla en tres capes perquè cadascuna tingui una sola feina: la vista avisa, el ViewModel decideix i la vista observa el resultat.

| Capa                | Fitxers                                                                              | Què fa                                                                                                                                                  |
| ------------------- | ------------------------------------------------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Model**     | `Validacions.kt`, `Comptes.kt`                                                   | Guarda les regles (com ha de ser un email, un telèfon…) i les dades (els comptes registrats a `RepositoriComptes`). No sap que existeixen pantalles. |
| **ViewModel** | `LoginViewModel.kt`, `RegistreViewModel.kt`                                      | Rep el que ha escrit l'usuari, ho comprova amb el Model i publica el resultat en `LiveData`. No toca mai cap vista.                                    |
| **View**      | `LoginFragment.kt`, `RegistreFragment.kt`, els seus XML i `ExtensionsVista.kt` | Dibuixa la pantalla, avisa el ViewModel quan es prem un botó i mostra el que li arriba pels `LiveData`. No decideix si una dada és correcta.         |

## Les classes ViewModel

### LoginViewModel

És el "cervell" de la pantalla d'inici de sessió. Té dues funcions:

- `entrar(usuari, contrasenya)`: la crida el Fragment en prémer «Entrar». Primer comprova el format dels camps (buit, massa curt, amb espais). Si hi ha algun error, s'atura. Si no, pregunta a `RepositoriComptes` si les credencials són correctes i posa `loginCorrecte` a `true` o mostra «Usuari o contrasenya incorrectes».
- `navegacioFeta()`: torna `loginCorrecte` a `false` després de navegar. Sense això, en girar el mòbil o tornar enrere, el `LiveData` encara valdria `true` i l'app navegaria sola una altra vegada.

No toca cap vista (ni `findViewById`, ni `Toast`, ni `TextInputLayout`) perquè el ViewModel viu més que la pantalla: si guardés una vista i girem el mòbil, apuntaria a una pantalla que ja no existeix. A més, així la lògica es pot provar sense Android.

### RegistreViewModel

Fa el mateix per al registre, amb sis camps: usuari, nom, cognoms, contrasenya, telèfon i correu. Té `crearCompte(...)` i `navegacioFeta()`.

`crearCompte(...)` valida **els sis camps alhora**: posa l'error de cada camp al seu `LiveData` i després comprova si tots estan bé:

```kotlin
val totCorrecte = listOf(
    _errorUsuari, _errorNom, _errorCognoms,
    _errorContrasenya, _errorTelefon, _errorEmail
).all { it.value == null }
```

Així l'usuari veu tots els errors de cop, no un per intent. Només si `totCorrecte` és `true` es desa el compte a `RepositoriComptes` i `registreCorrecte` passa a `true`.

## Els LiveData

Un `LiveData` és una capsa que guarda un valor i avisa sola els qui l'observen quan canvia. Només avisa quan la pantalla està activa i es dona de baixa sola quan es destrueix.

| ViewModel | LiveData                                                                                                | Què guarda                        | Qui l'observa i què fa                                      |
| --------- | ------------------------------------------------------------------------------------------------------- | ---------------------------------- | ------------------------------------------------------------ |
| Login     | `errorUsuari`                                                                                         | Id del missatge d'error o `null` | `LoginFragment`: pinta l'error sota la caixa d'usuari      |
| Login     | `errorContrasenya`                                                                                    | Id del missatge d'error o `null` | `LoginFragment`: pinta l'error sota la contrasenya         |
| Login     | `loginCorrecte`                                                                                       | `true` / `false`               | `LoginFragment`: navega a Usuaris                          |
| Registre  | `errorUsuari`, `errorNom`, `errorCognoms`, `errorContrasenya`, `errorTelefon`, `errorEmail` | Id del missatge d'error o `null` | `RegistreFragment`: pinta cada error a la seva caixa       |
| Registre  | `registreCorrecte`                                                                                    | `true` / `false`               | `RegistreFragment`: mostra un Toast i obre l'ajuda inicial |

Els errors són `Int?` perquè guarden l'id d'un text de `strings.xml` (per exemple `R.string.error_email`), no el text. Així el ViewModel no necessita un `Context` i l'app es pot traduir sense tocar-lo.

**El patró privat / públic.** Cada dada es declara dues vegades:

```kotlin
private val _errorUsuari = MutableLiveData<Int?>()  // privat i modificable
val errorUsuari: LiveData<Int?> = _errorUsuari        // públic i només de lectura
```

- `_errorUsuari` és `private` i `MutableLiveData`: només el ViewModel el pot canviar amb `.value = …`.
- `errorUsuari` és la mateixa capsa, però exposada com a `LiveData`: el Fragment la pot observar, però no modificar.

Així les dades només canvien des d'un sol lloc, el ViewModel, i la vista no pot espatllar res.

## Observabilitat

El Fragment es subscriu a cada `LiveData` amb `observe`:

```kotlin
viewModel.errorUsuari.observe(viewLifecycleOwner) { error ->
    tilUsuari.mostraError(error)
}
```

`observe` rep dues coses: **qui escolta** (un cicle de vida) i **què fer** quan arriba un valor nou (la lambda). Cada vegada que el ViewModel canvia `_errorUsuari`, aquesta lambda s'executa sola.

Fem servir `viewLifecycleOwner` i no `this` perquè un Fragment té dues vides: la seva i la de la seva vista. Quan naveguem a una altra pantalla i tornem enrere, la vista es destrueix i es torna a crear, però el Fragment continua viu. Amb `this`, en tornar ens subscriuríem una altra vegada sense haver-nos donat de baixa: tindríem dos avisos, i un intentaria pintar en una vista que ja no existeix. Amb `viewLifecycleOwner`, la subscripció mor amb la vista i mai es duplica.

## Funcions d'extensió

Una funció d'extensió afegeix un mètode nou a una classe que no és nostra, sense modificar-la. Ja les fèiem servir a `Navegacio.kt` (`navegarA`, `mostrarMenuPrincipal`).

**`Validacions.kt` (Model)**, sobre `String`: `esUsuariValid()`, `esEmailValid()`, `esContrasenyaValida()`, `esTelefonValid()` i `esNomValid()`. Cada regla viu en un sol lloc i Login i Registre la comparteixen.

```kotlin
fun String.esTelefonValid(): Boolean =
    length == 9 && all { it.isDigit() }

"612345678".esTelefonValid()   // true
"612-34".esTelefonValid()      // false
```

**`ExtensionsVista.kt` (View)**, sobre `TextInputLayout`:

- `textEscrit()`: retorna el text de la caixa, o `""` si és buida.
- `mostraError(idMissatge)`: mostra el missatge en vermell sota la caixa, o l'amaga si rep `null`.

```kotlin
viewModel.entrar(tilUsuari.textEscrit().trim(), tilContrasenya.textEscrit())
tilUsuari.mostraError(R.string.error_usuari)
```

## Regles de validació

| Camp                   | Regla                                       | Missatge d'error                            |
| ---------------------- | ------------------------------------------- | ------------------------------------------- |
| Qualsevol              | No pot estar buit                           | Aquest camp és obligatori                  |
| Usuari                 | Mínim 3 caràcters, sense espais           | Mínim 3 caràcters, sense espais           |
| Usuari (registre)      | No pot existir ja                           | Aquest nom d'usuari ja existeix             |
| Contrasenya (registre) | Mínim 8 caràcters, amb lletres i números | Mínim 8 caràcters, amb lletres i números |
| Nom i cognoms          | Només lletres, espais, guions o apòstrofs | Només lletres, i no pot estar buit         |
| Telèfon               | Exactament 9 xifres                         | Ha de tenir 9 xifres                        |
| Correu                 | Format `nom@domini.ext`                   | Correu no vàlid (exemple: nom@domini.cat)  |
| Login                  | Usuari i contrasenya han de coincidir       | Usuari o contrasenya incorrectes            |

## Com provar-ho

Amb la Compte de prova admin / admin1234 i els casos que es veuen al vídeo, link:
