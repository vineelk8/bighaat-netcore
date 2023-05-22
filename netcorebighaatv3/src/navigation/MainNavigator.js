import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import {createDrawerNavigator} from '@react-navigation/drawer';

import Home from '../screens/Home';
import About from '../screens/About';
import Contact from '../screens/Contact';

const screenOptionStyle = {
  headerShown: false,
  headerStyle: {
    backgroundColor: '#9AC4F8',
  },
  headerTintColor: 'white',
  headerBackTitle: 'Back',
};

const StackNavigation = createStackNavigator();

const HomeStackNavigator = () => {
  return (
    <StackNavigation.Navigator screenOptions={screenOptionStyle}>
      <StackNavigation.Screen name="Home" component={Home} />
    </StackNavigation.Navigator>
  );
};

const AboutStackNavigator = () => {
  return (
    <StackNavigation.Navigator screenOptions={screenOptionStyle}>
      <StackNavigation.Screen name="About" component={About} />
    </StackNavigation.Navigator>
  );
};

const TabNavigation = createBottomTabNavigator();

const BottomTabNavigator = () => {
  return (
    <TabNavigation.Navigator
      screenOptions={{
        headerShown: false,
      }}>
      <TabNavigation.Screen name="HomeTab" component={HomeStackNavigator} />
      <TabNavigation.Screen name="AboutTab" component={AboutStackNavigator} />
    </TabNavigation.Navigator>
  );
};

const MainStackNavigator = () => {
  return (
    <StackNavigation.Navigator screenOptions={screenOptionStyle}>
      <StackNavigation.Screen name="MainTabs" component={BottomTabNavigator} />
      <StackNavigation.Screen name="Contact" component={Contact} />
    </StackNavigation.Navigator>
  );
};

const DrawerNavigation = createDrawerNavigator();

const DrawerNavigator = () => {
  return (
    <DrawerNavigation.Navigator>
      <DrawerNavigation.Screen
        name="MainStack"
        component={MainStackNavigator}
      />
    </DrawerNavigation.Navigator>
  );
};

export {MainStackNavigator, DrawerNavigator};
