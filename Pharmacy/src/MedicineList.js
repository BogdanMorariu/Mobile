/**
 * Created by Bogdan on 10.11.2017.
 */
import * as React from 'react';
import * as firebase from 'firebase';
import { Text, View, FlatList, Button, AsyncStorage, Alert } from 'react-native';

export class MedicineList extends React.Component {
	static navigationOptions = {
		title: "Medicine list",
	};

	constructor(props) {
        super(props);
        this.state = {
			list : [],
			userRole: '' 
        }
      }

    componentDidMount(){
		this.getMedicineList();
	}
	
	async getMedicineList(){
		firebase.database().ref().child('medicine').on('value', (snapshot) => {
			const list = [];
			console.log(snapshot);
			snapshot.forEach((medicine) => {
				list.push({ 
					...medicine.val(), 
					id: medicine.key
				})
			});
			AsyncStorage.getItem('userRole').then((userRole) => {
				this.setState({list, userRole});
			});
		});
	}

	updateMedicine = (item) => {
		firebase.database().ref().child('medicine').child(item.id).set({
			name: item.name,
			producer: item.producer,
			description: item.description,
			price: item.price,
			id: item.id,
			history: item.history
		  });
	};

	addMedicine = (item) => {
		console.log(item);
		var newKey = firebase.database().ref().child('medicine').push().key;
		
		firebase.database().ref().child('medicine').child(newKey).set({
			name: item.name,
			producer: item.producer,
			description: item.description,
			price: item.price,
			id: newKey,
			history: item.history
		  });
	};

	deleteMed = (itemId) => {
		firebase.database().ref().child('medicine').child(itemId).remove();
	};
	
	
	onMedicinePressed(item) {
		const {navigate} = this.props.navigation;
        navigate('Details', {item: item, updateFunction: this.updateMedicine, isEdit: true});
	};

	_renderItem = (item) => {
		
	const {userRole} = this.state;
	const deleteButton = userRole === 'ADMIN' && (<Button
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
	/>);

	return (
		<View style={{flexDirection:'row', flexWrap:'wrap'}}>
			<Text onPress={() => {this.onMedicinePressed(item.item);}} style={{fontSize: 24, margin: 10}}>
				{item.item.name}
			</Text>
			<View style={{width: 30}}>
				{deleteButton}
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
			history: '1',
			price: 1
		};
        navigate('Details', {item: item, updateFunction: this.addMedicine, isEdit: false});
	};

	logout = async () => {
		try {
			await AsyncStorage.clear(); 
			const {navigate} = this.props.navigation;
			navigate('Main');
	
		} catch (error) {
			console.log(error);
		}
	}

    render(){
		const articleList = this.state.list;
		const { userRole } = this.state;
		const orderButton = userRole === 'USER' && (<Button
			onPress={() => {
				const item = {
					id: 0,
					name: '',
					producer: '',
					description: '',
					history: '',
					price: 0
				};
				const {navigate} = this.props.navigation;
				navigate('Details', {item, updateFunction: null, isEdit: false});
			}}
			title={"Order"}
		/>);

		const addButton = userRole === 'ADMIN' && (
			<Button
			onPress={this.addNew}
			title={"Add new"}
		/>
		);

		return(
			<View>
				<Button
					onPress={() => {this.logout();}}
					title={"Logout"}
				/>
				<FlatList
					data={this.state.list}
					renderItem={this._renderItem}
					keyExtractor={(item, index) => index}
				/>
				{orderButton}
				{addButton}
			</View>
		);
    }
}