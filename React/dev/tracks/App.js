import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import {createStackNavigator} from 'react-navigation-stack'
import {createBottomTabNavigator} from 'react-navigation-tabs'
import { createAppContainer, createSwitchNavigator} from 'react-navigation'
import SigninScreen from './src/screens/SigninScreen'
import SignupScreen from './src/screens/SignupScreen'
import TrackCreateScreen from './src/screens/TrackCreateScreen'
import TrackDetailsScreen from './src/screens/TrackDetailsScreen'
import TrackListScreen from './src/screens/TrackListScreen'
import AccountScreen from './src/screens/AccountScreen';
import ResolveAuth from './src/screens/ResolveAuth';
import {Provider as AuthProvider} from './src/context/AuthContext'
import {setMavigator, setNavigator} from './src/navigationRef'
const switchNavigator = createSwitchNavigator({
  ResolveAuths: ResolveAuth,
  loginFlow: createStackNavigator({
    Signup: SignupScreen,
    Signin: SigninScreen
  }),
  mainFlow: createBottomTabNavigator({
    TrackCreate: TrackCreateScreen,
    trackListFlow: createStackNavigator({
      TrackList: TrackListScreen,
      TrackDetail: TrackDetailsScreen
    }),
    Account: AccountScreen,
  })

});

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
const App = createAppContainer(switchNavigator);
export default () => {
  return (
    <AuthProvider>
      <App ref = {(navigator) => { setNavigator(navigator) } }/>
    </AuthProvider>
  )
};