/* config-overrides.js */
const { ModuleFederationPlugin } = require("webpack").container;

module.exports = function override(config, env) {
    //do stuff with the webpack config...
    config.output.publicPath = '/ui-home/';
    console.log(config.plugins);

    var moduleFederationPlugin = new ModuleFederationPlugin(
      {
        name: 'home',
        filename:
          'remoteEntry.js',
          remotes: {
            app1:
              'app1@http://localhost:8090/ui-app1/remoteEntry.js',
          }
      }
    );

    config.plugins.push(moduleFederationPlugin)
    return config;
  }