/**
 * Created by Bogdan on 10.11.2017.
 */
import * as React from 'react';
import { Text, View, FlatList, Button, AsyncStorage, Alert } from 'react-native';
import {update, add, deleteMedicine} from './MedicineRepo';

export class MedicineList extends React.Component {
	static navigationOptions = {
		title: "Medicine list",
	};

	constructor(props) {
        super(props);
        this.state = {
            list : []
        }
      }

    componentDidMount(){
		this.getMedicineList();
	}
	
	async getMedicineList(){
		let response = await AsyncStorage.getItem('medicineList'); 
		let medicineList = await JSON.parse(response); 

        if(!this._arraysAreEqual(this.state.list, medicineList)){

			this.setState({list: medicineList});
        }
	}

	_arraysAreEqual(array1, array2){
		if (array1.length !== array2.length){
            return false;
        }

        for(let i = 0; i < array1.length; i++){
            if (!this._medicinesAreEqual(array1[i], array2[i])){
                return false;
            }
        }

        return true;
	}

	_medicinesAreEqual(med1, med2) {
		return med1.id === med2.id && med1.name === med2.name
			&& med1.description === med2.description && med1.producer === med2.producer
			&& med1.priceHistory === med2.priceHistory;
	}

	updateMedicine = (item) => {
		update(item);
		this.getMedicineList();
	};

	addMedicine = (item) => {
		add(item);
		this.getMedicineList();
	};

	deleteMed = (itemId) => {
		deleteMedicine(itemId);
		this.getMedicineList();
	};
	
	
	onMedicinePressed(item) {
		const {navigate} = this.props.navigation;
        navigate('Details', {item: item, updateFunction: this.updateMedicine, isEdit: true});
	};

	_renderItem = (item) => {
		return (
			<View style={{flexDirection:'row', flexWrap:'wrap'}}>
				<Text onPress={() => {this.onMedicinePressed(item.item);}} style={{fontSize: 24, margin: 10}}>
					{item.item.name}
				</Text>
				<View style={{width: 30}}>
					<Button
						onPress={ () => {
							Alert.alert(
								'Are you sure you want to delete ' + item.item.name + '?',
								'',
								[
								  {text: 'Cancel', onPress: () => {}},
								  {text: 'OK', onPress: () => this.deleteMed(item.item.id)},
								],
								{ cancelable: false }
							  );
							}}
						title={"X"}
					/>
				</View>
			</View>);
    };

	addNew = () => {
        const {navigate} = this.props.navigation;
        let item = {
        	id: 0,
        	name: '',
			producer: '',
			description: '',
			priceHistory: []
		};
        navigate('Details', {item: item, updateFunction: this.addMedicine, isEdit: false});
	};

    render(){
        this.getMedicineList();
		const articleList = this.state.list;

        return(
            <View>
                <FlatList
					data={this.state.list}
					renderItem={this._renderItem}
					keyExtractor={(item) => item.id}
				/>
				<Button
					onPress={this.addNew}
					title={"Order"}
				/>
            </View>
        );
    }
}