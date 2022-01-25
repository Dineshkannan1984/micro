/* config-overrides.js */
const { ModuleFederationPlugin } = require("webpack").container;

module.exports = function override(config, env) {
    //do stuff with the webpack config...
    // config.output.publicPath = '/ui-app1/';

    console.log("print");
    console.log(config);
    console.log("print end");

    var moduleFederationPlugin = new ModuleFederationPlugin(
      {
        name: 'app1',
        filename:
          'remoteEntry.js',
        exposes: {
          './App11':'./src/App11'
        },
      }
    );
    
    config.output.publicPath= 'http://localhost:3001/ui-app1/'
    config.plugins.push(moduleFederationPlugin)
    return config;
  }