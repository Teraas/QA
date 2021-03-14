import React, {useState, useContext} from 'react'
import { View, StyleSheet } from 'react-native'
import { SafeAreaView } from 'react-navigation'
import { Text, Button, Input} from 'react-native-elements'
import {Context as AuthContext} from '../context/AuthContext'
import { AsyncStorage } from 'react-native'

const UserProfileScreen = ({navigation}) => {
    const { state, updateuser} = useContext(AuthContext);
    const [email, setEmail] = useState('');
    const [firstName, setfirstName] = useState('');
    const [familyName, setfamilyName] = useState('');
    const [motherName, setmotherName] = useState('');
    const [fatherName, setfatherName] = useState('');

    //const email = AsyncStorage.getItem('user_id');
    console.log("email");
    return (
        //<View style={styles.screen}>
        <SafeAreaView forceInset={{top: 'always'}} style={styles.profile}>
            <Text style={styles.fields} h3>My Profile</Text>
            <Input label="Email" 
                value={email} 
                onChangeText={setEmail}
                autoCapitalize= "none"
                autoCorrect={false}
                />
            <Input label="First Name" 
                value={firstName} 
                onChangeText={setfirstName}
                autoCapitalize= "none"
                autoCorrect={false}
                />
            <Input label="Family Name" 
                value={familyName} 
                onChangeText={setfamilyName}
                autoCapitalize= "none"
                autoCorrect={false}
                placeholder=" eg. Swami"
                />
            <Input label="Mother Name" 
                value={motherName} 
                onChangeText={setmotherName}
                autoCapitalize= "none"
                autoCorrect={false}
                />
            <Input label="Father Name" 
                value={fatherName} 
                onChangeText={setfatherName}
                autoCapitalize= "none"
                autoCorrect={false}
                />
            <Button title="Update Profile"
                onPress={() => updateuser({email, firstName, familyName, motherName, fatherName})}
                />
        </SafeAreaView>
        //</View>
    );
};

const styles = StyleSheet.create({
    screen: {
        justifyContent: 'center'
    },
    profile: {
        justifyContent: 'center',
        marginTop: 50,
        marginBottom: 200,
        marginLeft: 20,
        marginRight: 20
    },
    fields: {
        marginBottom: 30,
        marginLeft: 10
    },
    button: {
        marginBottom: 30,
        marginLeft: 10
    }
})

export default UserProfileScreen;