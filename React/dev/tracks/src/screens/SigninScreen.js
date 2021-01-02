import React, {useState, useContext} from 'react'
import { View, StyleSheet, TouchableOpacity } from 'react-native'
import { Text, Button, Input} from 'react-native-elements'
import MarginSpacer from '../components/MarginSpacer'
import {Context as AuthContext} from '../context/AuthContext'

const SigninScreen = ({navigation}) => {
    const { state, signin} = useContext(AuthContext);
const [email, setEmail] = useState('');
const [password, setPassword] = useState('');

console.log(state);

    return (
        <View style={styles.container}>  
            <MarginSpacer>
                <Text h3>Signin for the app</Text>
            </MarginSpacer>
            <MarginSpacer>
                <Input label="Email" 
                value={email} 
                onChangeText={setEmail}
                autoCapitalize= "none"
                autoCorrect={false}
                />
            </MarginSpacer>
            <MarginSpacer>
                <Input label="Password" 
                value={password} 
                onChangeText={setPassword}
                autoCapitalize= "none"
                autoCorrect={false}
                secureTextEntry={true}
                />
            </MarginSpacer>
            {state.errorMessage ? <Text style={styles.error}>{state.errorMessage}</Text> : null}
            <MarginSpacer>
                <Button title="Signin" 
                onPress={() => signin({email, password})}
                />
            </MarginSpacer>
            <TouchableOpacity  onPress={() => navigation.navigate('Signup')}>
                <MarginSpacer>
                <Text style={styles.link}>Not having an account? Sign up here</Text>
                </MarginSpacer>
            </TouchableOpacity>

        </View>
        
    );
};

SigninScreen.navigationOptions = {
    headerShown: true
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        marginBottom: 200
    },
    error: {
        color: 'red',
        fontSize: 16,
        marginLeft: 15
    },
    link : {
        color: 'blue'
    }
})

export default SigninScreen;