/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './App';
import {name as appName} from './app.json';

AppRegistry.registerHeadlessTask('ExampleHeadlessTask', taskData => {
  console.log(
    'ExampleHeadlessTask JS executing. Received taskData: ' +
      JSON.stringify(taskData),
  );
});

AppRegistry.registerComponent(appName, () => App);
