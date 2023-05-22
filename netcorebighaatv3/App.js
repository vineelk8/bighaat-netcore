import React, {useState, useEffect} from 'react';
import {View} from 'react-native';
import {NavigationContainer} from '@react-navigation/native';
import {SafeAreaProvider} from 'react-native-safe-area-context';

import {
  DrawerNavigator,
  MainStackNavigator,
} from './src/navigation/MainNavigator';
import AppStartUp from './src/components/AppStartUp';
import HiddenWebView from './src/components/HiddenWebView';

const App = () => {
  const [hasNavigation, setHasNavigation] = useState(false);

  useEffect(() => {
    setTimeout(() => {
      setHasNavigation(true);
    }, 3000);
  }, []);

  let contentToRender = <AppStartUp />;

  if (hasNavigation) {
    contentToRender = <DrawerNavigator />;
  }

  return (
    <SafeAreaProvider>
      <View
        style={{flex: 1}}
        testID={'4#1'}
        nativeID={'hansel_ignore_view_overlay'}>
        <NavigationContainer>
          <View style={{display: 'none'}}>
            <HiddenWebView />
          </View>
          {contentToRender}
        </NavigationContainer>
      </View>
    </SafeAreaProvider>
  );
};

export default App;
