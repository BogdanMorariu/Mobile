import * as React from 'react';
import {StackNavigator} from 'react-navigation';
import {MedicineList} from './src/MedicineList';
import {MedicineDetails} from './src/MedicineDetails';
import {AsyncStorage} from 'react-native';
import {getList} from './src/MedicineRepo';

const Navigator = StackNavigator({
    Main: { screen: MedicineList },
    Details: { screen: MedicineDetails },
});

export default class App extends React.Component {

    componentWillMount(){
        this._populateList();
    }
    
    async _populateList(){
        await AsyncStorage.setItem('medicineList', JSON.stringify(getList()));
    }

    render() {
        return (
            <Navigator/>
        );
    }
}

