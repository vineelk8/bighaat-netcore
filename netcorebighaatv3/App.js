import React from 'react';
import {NavigationContainer} from '@react-navigation/native';

import {
  DrawerNavigator,
  MainStackNavigator,
} from './src/navigation/MainNavigator';

const App = () => {
  return (
    <NavigationContainer>
      <DrawerNavigator />
    </NavigationContainer>
  );
};

export default App;
