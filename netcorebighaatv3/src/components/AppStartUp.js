import React from 'react';
import {View, ActivityIndicator} from 'react-native';

const AppStartUp = () => {
  return (
    <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
      <ActivityIndicator size="large" color="red" />
    </View>
  );
};

export default AppStartUp;
