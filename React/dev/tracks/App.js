import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import {createStackNavigator} from 'react-navigation-stack'
import {createBottomTabNavigator} from 'react-navigation-tabs'
import { createAppContainer, createSwitchNavigator} from 'react-navigation'
import SigninScreen from './src/screens/SigninScreen'
import SignupScreen from './src/screens/SignupScreen'
import HomeScreen from './src/screens/HomeScreen'
import FamilyTreeScreen from './src/screens/FamilyTreeScreen'
import UserProfileScreen from './src/screens/UserProfileScreen'
import FamilyHistoryScreen from './src/screens/FamilyHistoryScreen';
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
    Home: HomeScreen,
    FamilyTree: FamilyTreeScreen,
    Profile: UserProfileScreen,
    //History: FamilyHistoryScreen,
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