/**
 * Created by Bogdan on 10.11.2017.
 */
import * as React from 'react';
import { Text, View, FlatList, Button } from 'react-native';
import {getList, update} from './medicineRepo';

export class MedicineList extends React.Component {
	static navigationOptions = {
		title: "Medicine list",
	};

    constructor(props){
        super(props);
        this.state = {
        	list: getList()
        }
    };

	updateMedicine = (item) => {
		update(item);
	};
	
	onMedicinePressed(item) {
		const {navigate} = this.props.navigation;
        navigate('Details', {item: item, updateFunction: this.updateMedicine, isEdit: true});
	};

	_renderItem = (item) => {
		return (<Text onPress={() => {this.onMedicinePressed(item.item);}} style={{fontSize: 24}}>
            {item.item.name}
		</Text>);
    };

	addNew = () => {
        const {navigate} = this.props.navigation;
        let item = {
        	id: 0,
        	name: '',
			producer: '',
			description: ''
		};
        navigate('Details', {item: item, updateFunction: null, isEdit: false});
	};

    render(){
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