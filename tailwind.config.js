const {scanClojure} = require('@multiplyco/tailwind-clj');

module.exports = {
  content: {
       files: [
          './src/**/*.{clj,cljs,cljc}'
          ],
      extract: {
        clj: (content) => scanClojure(content),
        cljs: (content) => scanClojure(content),
        cljc: (content) => scanClojure(content)
      }
  }
}