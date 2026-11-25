# OrientaApp — Documentació de l'aplicació

## 1. Què és i com es navega

OrientaApp és una app d'orientació educativa. Una sola `Activity` (`MainActivity`)
allotja totes les pantalles com a `Fragment`s dins de `fragmentContainerView`;
`SplashActivity` és la pantalla d'arrencada que dona pas a `MainActivity`.

Mapa de navegació:

```
SplashActivity
  └─ LogoFragment (toc a la pantalla)
        └─ LoginFragment
              ├─ "Registrar-se" ─────────────► RegistreFragment
              │                                    └─ "Ja tens usuari?" torna enrere (tornarEnrere)
              └─ "Entrar" ───────────────────► UsuarisFragment  (destí principal, buida la pila)

Barra inferior (sempre visible a Usuaris / Qüestionaris / Filtre):
  UsuarisFragment ⇄ QuestionarisFragment ⇄ FiltreFragment   (navegarAPrincipal: no s'apilen entre elles)

Des de qualsevol de les tres anteriors, la barra superior permet:
  "+"  → afegir un usuari / crear un qüestionari
  "⋮"  → obre un menú real (PopupMenu, mostrarMenuPrincipal) amb:
           Configuració · Dades personals · Ajuda · Tancar sessió (buida la pila i torna al Login)

UsuarisFragment    → tocar una fila obre UsuariFragment (afegir/editar usuari)
QuestionarisFragment → tocar una fila obre EditarQuestionariFragment (editar qüestionari)
```

Totes les navegacions "cap endavant" usen `navegarA` (apila, es pot tornar enrere) excepte
els destins de primer nivell (login → llista, tancar sessió, canvi de pestanya de la barra
inferior), que usen `navegarAPrincipal` (buida la pila, no té sentit tornar-hi enrere).
Les fletxes `←` de les barres superiors criden `tornarEnrere`. Totes aquestes funcions
viuen a `Navegacio.kt` perquè no es dupliqui la lògica a cada `Fragment`.

## 2. Arquitectura del codi

- Cada llista (`UsuarisFragment`, `QuestionarisFragment`) segueix el mateix patró:
  `Fragment` + `RecyclerView.Adapter` (`UsuarisAdapter` / `QuestionarisAdapter`) + `ViewHolder`
  (`UsuariHolder` / `QuestionariHolder`) + un repositori en memòria (`RepositoriUsuaris` /
  `RepositoriQuestionaris`). L'`Adapter` no coneix Toasts ni diàlegs: rep les accions
  (modificar/editar, esborrar) com a funcions lambda i el `Fragment` decideix què fer-hi.
- Els formularis d'afegir/editar (`UsuariFragment`, `CrearQuestionariFragment`,
  `EditarQuestionariFragment`) encara no desen les dades introduïdes: de moment tornen
  enrere en prémer "Guardar" (igual que la resta de l'app, que funciona amb dades de
  prova en memòria, no amb una base de dades real).
- Els colors viuen a `colors.xml` (mode clar) i `values-night/darkmode.xml` (mode fosc),
  amb els mateixos noms de recurs (`primari`, `superficie`, `text_principal`...), i el tema
  `Theme.OrientaApp` només referencia aquests noms.

## 3. Usabilitat

- **Confirmació abans d'esborrar**: esborrar un usuari o un qüestionari sempre mostra un
  `MaterialAlertDialogBuilder` ("Aquesta acció no es pot desfer") amb un botó per cancel·lar,
  perquè un toc accidental no eliminï dades.
- **Retroalimentació de cada acció**: afegir, modificar i esborrar mostren un `Toast` amb el
  nom de l'element afectat, a més de registrar-se al Logcat.
- **Mida dels controls tàctils**: els botons principals (`BotoPrincipal`, `BotoSecundari`) tenen
  `minHeight="56dp"`, per sobre del mínim de 48dp recomanat, per facilitar-hi el toc.
- **Control adequat per a cada tipus de dada**: al filtre, l'edat (un rang numèric acotat) es
  selecciona amb un `Slider` i l'àmbit (un conjunt fix de categories) amb `Chip`s de selecció
  única, en lloc d'un `TextInput` de text lliure, que seria més lent i més propens a errors
  d'escriptura per a aquest tipus de dada.
- **Navegació consistent**: la barra inferior sempre marca la pestanya activa
  (`configurarBarraInferior`) i mai apila pantalles principals entre elles, així l'usuari no
  acaba amb una pila llarga de pantalles iguals en prémer enrere repetidament.
- **Menú real**: l'acció "⋮" obre un menú d'opcions (`PopupMenu`) amb les accions disponibles,
  en lloc de navegar directament a una pantalla com si fos un botó més; així l'usuari pot veure
  totes les opcions abans de triar-ne una.

## 4. Accessibilitat

- **Text alternatiu a les icones**: tots els controls que només mostren una icona tenen
  `contentDescription` (`desc_logo`, `desc_esborrar_usuari`, `desc_esborrar_questionari`,
  `enrere`, `logoDescription` a les toolbars), perquè un lector de pantalla els pugui anunciar.
  Les icones purament decoratives (com la de cada fila de la llista) es marquen
  `importantForAccessibility="no"` perquè no generin soroll als lectors de pantalla.
- **Contrast de color**: els colors de text (`text_principal`, `text_secundari`) estan definits
  per separat del color de superfície (`superficie`/`fons`) tant en mode clar com en mode fosc,
  mantenint un contrast suficient en tots dos temes (`DayNight`).
- **Mida de text llegible**: els estils de text (`TitolPantalla` 24sp, `TextNormal`/`TextEtiqueta`
  16sp) estan per sobre de la mida mínima recomanada de 12sp i fan servir `sp` (no `dp`), de
  manera que respecten la mida de lletra que l'usuari hagi configurat al sistema.
- **Respecte de les barres del sistema**: `MainActivity` aplica el `WindowInsets` de les barres
  del sistema com a padding (`enableEdgeToEdge` + `setOnApplyWindowInsetsListener`), perquè el
  contingut no quedi amagat ni superposat sota la barra d'estat o de navegació.
- **Diàlegs de confirmació**: eviten pèrdues de dades accidentals, un aspecte d'accessibilitat
  cognitiva tan important com la part visual.

## 5. Limitacions conegudes / pendent

- Els formularis d'afegir/editar (`UsuariFragment`, `CrearQuestionariFragment`,
  `EditarQuestionariFragment`) no persisteixen encara els valors introduïts (de moment és
  una simulació amb dades en memòria; es preveu connectar-ho a una base de dades real).
- A `UsuarisFragment`, tocar una fila encara simula l'edició (puja 1 any l'edat) en lloc
  d'obrir `UsuariFragment` amb les dades precarregades, perquè encara no hi ha un mecanisme
  per passar l'usuari seleccionat entre fragments; a `QuestionarisFragment` sí que s'ha
  implementat aquest pas (tocar una fila obre `EditarQuestionariFragment`).
