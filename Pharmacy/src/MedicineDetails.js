/**
 * Created by Bogdan on 10.11.2017.
 */
import * as React from 'react';
import { Text, View, TextInput, Button, Picker} from 'react-native';
import Communications from 'react-native-communications';
import {Chart} from './Chart';

export class MedicineDetails extends React.Component{

    static navigationOptions = {
        title: "Medicine details",
    };

    constructor(props){
        super(props);
        const item = this.props.navigation.state.params.item;
        this.state = {
            id: item.id,
            name: item.name,
            producer: item.producer,
            description: item.description,
            updateFunction: this.props.navigation.state.params.updateFunction,
            isEdit: this.props.navigation.state.params.isEdit,
            priceHistory: item.history,
            quantity: "0",
            price: JSON.stringify(item.price)
        };
    }

    save = () => {
        const item = {
            id: this.state.id,
            name: this.state.name,
            producer: this.state.producer,
            description: this.state.description,
            history: this.state.priceHistory,
            price: parseFloat(this.state.price)
        };

        if (this.state.isEdit){
            item.history = item.history.concat(';' + this.state.price);
        } else {
            item.history = this.state.price;
        }

        if(!this.state.updateFunction) {
            Communications.email(["tipitza@gmail.com"], null, null, 'Order for medicine', this.state.name + ' was ordered in a quantity of ' + this.state.quantity);
        }
        else {
            this.state.updateFunction(item);
        }

        const {navigate} = this.props.navigation;
        navigate('List');
    };

    formatHistory(history) {
        let result = history.split(';');
        if (result.length === 0) 
            result = [0];
        else
            result = result.map(h => parseFloat(h));
        console.log(result);
        return result;
	}

    render(){
        const buttonTitle = this.state.updateFunction ? "Save" : "Order medicine";

        const picker = !this.state.updateFunction &&  
        (
        <View style={{marginTop: 30}}>
            <Text>
                Quantity:
            </Text>
            <Picker
                selectedValue={this.state.quantity}
                onValueChange={(itemValue, itemIndex) => {
                    this.setState({quantity: itemValue});
                }}>
                <Picker.Item label="--- Select quantity ---" value="none" />
                <Picker.Item label="1" value="1" />
                <Picker.Item label="2" value="2" />
                <Picker.Item label="3" value="3" />
                <Picker.Item label="4" value="4" />
                <Picker.Item label="5" value="5" />
            </Picker>
        </View>);

console.log("before chart");
        const chart = this.state.isEdit && 
        (
        <View>
            <Text>
                Price evolution:
            </Text>
            <Chart prices={this.formatHistory(this.state.priceHistory)}/>
        </View>
        );

        console.log("after chart");

        const priceInput = this.state.updateFunction && 
        (
            <View>
                <Text>
                    Price:
                </Text>
                <TextInput
                    value={this.state.price}
                    onChangeText={(Price) => { this.setState({price: Price}); }}
                />
            </View>);

console.log("after price");
        return(
            <View>
                <Text>
                    Name:
                </Text>
                <TextInput
                    value={this.state.name}
                    onChangeText={(Name) => this.setState({name: Name})}
                />
                <Text>
                    Producer:
                </Text>
                <TextInput
                    value={this.state.producer}
                    onChangeText={(Producer) => this.setState({producer: Producer})}
                />
                
                {picker}

                <Text>
                    Description:
                </Text>
                <TextInput
                    value={this.state.description}
                    onChangeText={(Description) => this.setState({description: Description})}
                />

                {priceInput}

                <Button
                    onPress={this.save}
                    title={buttonTitle}
                />

                {chart}

            </View>
        );
    }
}