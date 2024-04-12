

# Jaxan's Electric Starter App
Based off the Electric Starter App template, this template contains setups for a
few additional features
1) TailWindCSS for Styling
2) Automatic Versioning and simplistic release pipeline integrated into build tools (windows only)
3) System Tray Icon and Menu
4) Portal Setup and Kindly Data
5) VS Code setups for Calva Repl, Tailwind extension and others
6) A Basic Application controls setup for startup and running in dev or production modes
7) Bat Files for the windows terminal commands to facilitate ease of use and
   make it easy for beginners to know what to do
8) Font-Awesome and Icons through Iconify


## TailWindCSS specifics
- File will rebuild alongside the hot reloading features of the Electric
  Template. It takes a little bit more time to recompile and apply the file so
  you will see a delay if you update and add new TailWind Classes. If you are
  doing many of these changes (Working on the front end) then you will want to
  run the RunTailWindWatch.bat file which will cause the recompile to happen
  faster. It is a separate process though, so only start it when you are wanting
  more instant feedback for style changes.
- A convention is built into the VS Code setup so that the TailWindCSS extension
  works with clojure. It is simple REGEX in the source code so be aware the
  following patterns will interpret the strings as tailwind strings and thus
  show colors and provide autocomplete options for library of TailWind Classes

```Clojure
varnameStyle "Tailwind String..." 
{:class "TailWind String..."} 
{:class (str "TailWind String..." varnameStyle)}
```

## Suggested VS Code Extensions
- **Calva** (betterthantomorrow.calva)
- **Batch Runner** (NilsSoderman.batch-runner) - Gives ability to right click on windows bat file and run it
- **Peacock** (johnpapa.vscode-peacock) - Gives ability to set color of VS Code window for the project (helpful when multiple projects open)
- **TailWindCSS IntelliSense** (bradlc.vscode-tailwindcss) - Provides autocompletion and shows colors for tailwind classes
- **Material Design Icons Intellisense** (lukas-tr.materialdesignicons-intellisense) - Shows Icons for mdi icons and provides a browser to select icons

I also have some other personal preference suggestions if you scroll down to the end of this page.

## Automatic Versioning
Essentially you set a directory where your release files are to be stored, and
on the building of the clojure project the version number will get updated
automatically both in the jar file produced and in the version.clj file in the
code. The updated version number is based on an incremented version number from
the last released version of format   1.2.3   where the "3" would get
autoincremented to 4 on each release so the next version automatically versioned
will be 1.2.4

The nice thing is you can work on a release version for as long as you want and
the version will not increase until the released jar file actually shows up in
the release directory.

Note there is a bat file (RunLastestRelease.bat) that will find and run the
lastest release in the release directory as well but this only works for Windows
since it is based off the windows terminal. Need to write the linux and Mac
variants for all the bat files.

If use the run lastest release file as the bat file in my windows starter
program, then you can use the release directory as a network or cloud folder and
get most of the benefits of a continuous delivery pipeline without needing to
bug your users to install a patch with each bug fix.

# Electric Starter App (Original ReadMe Documentation)

A minimal Electric Clojure app, and instructions on how to integrate it into an
existing app. For more demos and examples, see [Electric
Fiddle](https://github.com/hyperfiddle/electric-fiddle).

## Instructions

Dev build:

* Shell: `clj -A:dev -X dev/-main`, or repl: `(dev/-main)`
* http://localhost:8080
* Electric root function: [src/TemplateElectric/main.cljc](src/TemplateElectric/main.cljc)
* Hot code reloading works: edit -> save -> see app reload in browser

Prod build:

```shell
clj -X:build:prod build-client
clj -M:prod -m prod
```

Uberjar (optional):
```
clj -X:build:prod uberjar :build/jar-name "target/app.jar"
java -cp target/app.jar clojure.main -m prod
```

Deployment example:
- [Dockerfile](Dockerfile)
- fly.io deployment through github actions: [.github/workflows/deploy.yml](.github/workflows/deploy.yml) & [fly.toml](fly.toml)

## Integrate it in an existing clojure app

1. Look at [src-prod/prod.cljc](src-prod/prod.cljc). It contains:
    - server entrypoint
    - client entrypoint
    - necessary configuration
2. Look at [src/TemplateElectric/server_jetty.clj](src/TemplateElectric/server_jetty.clj). It contains:
   - an example Jetty integration
   - required ring middlewares

## Build documentation

Electric Clojure programs compile down to separate client and server target programs, which are compiled from the same Electric application source code.

* For an Electric client/server pair to successfully connect, they must be built from matching source code. The server will reject mismatched clients (based on a version number handshake coordinated by the Electric build) and instruct the client to refresh (to get the latest javascript artifact).
* [src-build/build.cljc](src-build/build.clj bakes the Electric app version into both client and server artifacts.
  * server Electric app version is baked into `electric-manifest.edn` which is read in [src-prod/prod.cljc](src-prod/prod.cljc).
  * client Electric app version is baked into the .js artifact as `hyperfiddle.electric-client/ELECTRIC_USER_VERSION`

Consequently, you need **robust cache invalidation** in prod!
  * In this example, complied js files are fingerprinted with their respective hash, to ensure a new release properly invalidates asset caches. [index.html](resources/public/TemplateElectric/index.html) is templated with the generated js file name.
  * The generated name comes from shadow-cljs's `manifest.edn` file (in `resources/public/TemplateElectric/js/manifest.edn`), produced by `clj -X:build:prod build-client`. Watch out: this shadow-cljs compilation manifest is not the same manifest as `electric-manifest.edn`!
  * Notice that [src/TemplateElectric/server_jetty.clj](src/TemplateElectric/server_jetty.clj) -> `wrap-index-page` reads `:manifest-path` from config. The config comes from [src-prod/prod.cljc](src-prod/prod.cljc).


# Other Suggestions from my Personal Preferences

### VS Code Settings.json file
```JSON
{
    "[clojure]": {
        "editor.autoClosingBrackets": "never",
        "editor.autoClosingDelete": "never",
        "editor.autoClosingOvertype": "never",
        "editor.autoClosingQuotes": "never",
        "editor.autoSurround": "never",
        "editor.dragAndDrop": false,
        "editor.showFoldingControls": "always"
    },
    "calva.highlight.commentFormStyle": {},
    "calva.paredit.defaultKeyMap": "original",
    "editor.autoClosingBrackets": "never",
    "editor.autoClosingDelete": "never",
    "editor.autoClosingOvertype": "never",
    "editor.autoClosingQuotes": "never",
    "editor.emptySelectionClipboard": false,
    "editor.fontFamily": "Fira Code",
    "editor.fontLigatures": true,
    "editor.minimap.enabled": false,
    "explorer.confirmDragAndDrop": false,
    "git.autofetch": true,
    "git.confirmSync": false,
    "workbench.colorTheme": "Visual Studio Light",
    "calva.prettyPrintingOptions": {
        "printEngine": "pprint",
        "enabled": true,
        "width": 120,
        "maxLength": 50
    },
    "calva.keybindingsEnabled": true,
    "calva.html2HiccupOptions": {},

    "replacerules.rules": {
        "ConvertPathDelimiters": {
            "find": "\\\\",
            "replace": "\\\\"
        }
    }
}
```
I disable Paredit and autoclosing of brackets and quotes since I don't like
those features. I think they are a bit distracting for new programmers as well

I like Fira Code as a font since it adds ligatures to the code and can help
with readability. You will need to install Fira Code on your machine for this
font to work.

I also added the extension Replace Rules (bhughes339.replacerules) and this
gives me the ability to quickly fix the path delimiters when copying directory
paths.

I also use the extension Rewrap (stkb.rewrap) which gives the ability to use
Alt-q to wrap text when editing markdown files and comment strings when writting
longer comments in clojure.