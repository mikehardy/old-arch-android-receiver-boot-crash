/**
 * @format
 */

import {AppRegistry} from 'react-native';
import notifee from '@notifee/react-native';
import App from './App';
import {name as appName} from './app.json';

AppRegistry.registerHeadlessTask(
  'ExampleHeadlessTask',
  () => async taskData => {
    setImmediate(() => {
      console.log('This is running from ExampleHeadlessTask::setImmediate');
    });

    console.log(
      'ExampleHeadlessTask JS executing. Received taskData: ' +
        JSON.stringify(taskData),
    );

    // Display a notification
    await notifee.displayNotification({
      title: 'Notification Title',
      body: 'Main body content of the notification',
      android: {
        channelId: 'misc',
        // pressAction is needed if you want the notification to open the app when pressed
        pressAction: {
          id: 'default',
        },
      },
    });
  },
);

notifee.onBackgroundEvent(async event => {
  setImmediate(() => {
    console.log('This is running from notifee.onBacgroundEvent::setImmediate');
  });

  console.log('notifee.onBackgroundEvent with event: ' + JSON.stringify(event));
});

AppRegistry.registerComponent(appName, () => App);
