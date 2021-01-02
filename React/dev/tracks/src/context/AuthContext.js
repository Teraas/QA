import React, {useReducer} from 'react'
import { AsyncStorage } from 'react-native'
import createDataContext from './createDataContext'
import trackAPI from '../api/track'
import { navigate } from '../navigationRef'  // use {} if importing not a default export

const authReducer = (state, action) => {
    switch(action.type) {
        case 'add_error': 
            return { ...state, errorMessage: action.payload}
        case 'signup':
            return {errorMessage: '', token: action.payload}
            case 'signin':
                return {errorMessage: '', token: action.payload}
        default:
            return state;
    }
};

const signup = (dispatch) => {

    return async ({email, password}) => {
        // make api request to signup,
        // Once done, update the state
        // if fails, show error message

        try {
            const response = await trackAPI.post('/signup', { email, password });
            //console.log(response.data);
            console.log('in signup auth context');
            //store data in storage
            await AsyncStorage.setItem('token', response.data.token);

            dispatch({ type: 'signup', payload: response.data.token})
            navigate('TrackList');
        } catch (err) {
            console.log(err);
            dispatch({ type: 'add_error', payload: 'something went wrong'})
        }
    }
}

const signin = (dispatch) => {

    return async ({email, password}) => {
        // make api request to signin,
        // Once done, update the state
        // if fails, show error message

        try {
            const response = await trackAPI.post('/signin', { email, password });
            console.log('in signin auth context');
            await AsyncStorage.setItem('token', response.data.token);

            dispatch({ type: 'signin', payload: response.data.token})
            navigate('TrackList');
        } catch (err) {
            console.log(err);
            dispatch({ type: 'add_error', payload: 'something went wrong with signin'})
        }
    }
}

const signout = (dispatch) => {

    return ({email, password}) => {
        // make api request to signup,
        // Once done, update the state
        // if fails, show error message
        

    }
}

const localSignin = dispatch => async () => {
    const token = await AsyncStorage.getItem('token');
    if(token) {
        dispatch({ type: 'signin', payload: token})
        navigate('TrackList');
    } else {
        navigate('Signin');
    }
}
export const {Provider, Context} = createDataContext(
    authReducer,
    { signin, signup, signout, localSignin },
    { isSignedIn: false, errorMessage: ''}
);