import * as React from 'react';
import * as firebase from 'firebase';
import {StackNavigator} from 'react-navigation';
import {MedicineList} from './src/MedicineList';
import {MedicineDetails} from './src/MedicineDetails';
import {AsyncStorage} from 'react-native';
import { LoginScreen } from './src/LoginScreen';

// Initialize Firebase
var config = {
    apiKey: "AIzaSyAz0AIfxNStZUMLwldns8I_GSgd3n-eRq0",
    authDomain: "pharmacy-46195.firebaseapp.com",
    databaseURL: "https://pharmacy-46195.firebaseio.com",
    projectId: "pharmacy-46195",
    storageBucket: "pharmacy-46195.appspot.com",
    messagingSenderId: "327634361649"
  };

const Navigator = StackNavigator({
    Main: {screen: LoginScreen},
    List: { screen: MedicineList },
    Details: { screen: MedicineDetails },
});

export default class App extends React.Component {

    componentWillMount(){
        firebase.initializeApp(config);
        console.disableYellowBox = true;
    }

    render() {
        return (
            <Navigator/>
        );
    }
}

