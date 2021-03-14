import React, { useState, useEffect } from 'react'
import { View, StyleSheet } from 'react-native'
import { Text } from 'react-native-elements'
import { SafeAreaView } from 'react-navigation'
import { requestPermissionsAsync } from 'expo-location'


const HomeScreen = () => {
    const [err, setErr] = useState(null);
    const startWatching = async () => {
        try {
          const { granted } = await requestPermissionsAsync();
          if (!granted) {
            throw new Error('Location permission not granted');
          }
        } catch (e) {
          setErr(e);
        }
      };

    useEffect(() => {
        startWatching();
    }, []);

    return (
        <SafeAreaView forceInset={{top: 'always'}} style={styles.profile}>
            <Text h3>Home</Text>
            {err ? <Text>Refresh to get more activities</Text> : null}
        </SafeAreaView>
        
    );
};

const styles = StyleSheet.create({
  profile: {
    justifyContent: 'center',
    marginTop: 50,
    marginBottom: 200,
    marginLeft: 20,
    marginRight: 20
}
})

export default HomeScreen;