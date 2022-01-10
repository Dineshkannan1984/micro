/* config-overrides.js */
const { ModuleFederationPlugin } = require("webpack").container;

module.exports = function override(config, env) {
    //do stuff with the webpack config...
    config.output.publicPath = '/ui-app1/';
    console.log(config.plugins);

    var moduleFederationPlugin = new ModuleFederationPlugin(
      {
        name: 'app1',
        filename:
          'remoteEntry.js',
        exposes: {
          './App':'./src/App'
        },
      }
    );

    config.plugins.push(moduleFederationPlugin)
    return config;
  }