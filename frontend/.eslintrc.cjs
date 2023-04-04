/* eslint-env node */
require('@rushstack/eslint-patch/modern-module-resolution')

module.exports = {
  root: true,
  'extends': [
    'plugin:vue/vue3-essential',
    'eslint:recommended',
    '@vue/eslint-config-prettier/skip-formatting'
  ],
  parserOptions: {
    ecmaVersion: 'latest'
  },
  rules: {
    "vue/no-unused-vars": "off",
    "no-unused-vars": "off",
    "vue/no-mutating-props":"off",
    'prettier/prettier': [
      'error',
      {
        useTabs: false,
        endOfLine: 'auto',
      },
    ],
},
}
