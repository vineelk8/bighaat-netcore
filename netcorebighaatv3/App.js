import React from 'react';
import {View} from 'react-native';
import {NavigationContainer} from '@react-navigation/native';
import {SafeAreaView, SafeAreaProvider} from 'react-native-safe-area-context';

import {
  DrawerNavigator,
  MainStackNavigator,
} from './src/navigation/MainNavigator';

const App = () => {
  return (
    <SafeAreaProvider>
      <View
        style={{flex: 1}}
        testID={'4#1'}
        nativeID={'hansel_ignore_view_overlay'}>
        <NavigationContainer>
          <DrawerNavigator />
        </NavigationContainer>
      </View>
    </SafeAreaProvider>
  );
};

export default App;
