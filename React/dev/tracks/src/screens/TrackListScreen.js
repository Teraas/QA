import React from 'react'
import { View, StyleSheet } from 'react-native'
import { Text, Button, Input} from 'react-native-elements'
const TrackListScreen = ({navigation}) => {

    return (
        <View>
            <Text>TrackListScreen</Text>
            <Button title="Go to Track Details" onPress={() => navigation.navigate('TrackDetail')}/>
        </View>
        
    );
};

const styles = StyleSheet.create({

})

export default TrackListScreen;