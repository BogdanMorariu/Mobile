import * as React from 'react';
import {StackNavigator} from 'react-navigation';
import {MedicineList} from './src/MedicineList';
import {MedicineDetails} from './src/MedicineDetails';

const Navigator = StackNavigator({
    Main: { screen: MedicineList },
    Details: { screen: MedicineDetails },
});

export default class App extends React.Component {
    render() {
        return (
            <Navigator/>
        );
    }
}

