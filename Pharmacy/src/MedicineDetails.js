/**
 * Created by Bogdan on 10.11.2017.
 */
import * as React from 'react';
import { Text, View, TextInput, Button} from 'react-native';
import Communications from 'react-native-communications';

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
            isEdit: this.props.navigation.state.params.isEdit
        };
        //console.log(this.state);
    }

    save = () => {
        if(this.state.isEdit) {
            const item = {
                id: this.state.id,
                name: this.state.name,
                producer: this.state.producer,
                description: this.state.description
            };
            this.state.updateFunction(item);
        }else{
            //console.log("mail" + this.state.name);
            Communications.email(["tipitza@gmail.com"], null, null, 'Order for medicine', this.state.name + ' was ordered');
        }
        const {navigate} = this.props.navigation;
        navigate('Main');
    };

    render(){
        const buttonTitle = this.state.isEdit ? "Save" : "Order medicine";
        //console.log("weird" + this.state);
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
                <Text>
                    Description:
                </Text>
                <TextInput
                    value={this.state.description}
                    onChangeText={(Description) => this.setState({description: Description})}
                />
                <Button
                    onPress={this.save}
                    title={buttonTitle}
                />
            </View>
        );
    }
}