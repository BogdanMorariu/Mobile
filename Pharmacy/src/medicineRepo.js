/**
 * Created by Bogdan on 10.11.2017.
 */
import {AsyncStorage} from 'react-native';

let medicineListSeed = [
    {
        id: 1,
        name: "Vitamina C",
        producer: "ProfiPharm",
        description: "This is a description for Vitamin C",
        priceHistory: [4, 6, 8]
    },
    {
        id: 2,
        name: "Nurofen",
        producer: "GSK",
        description: "This is a description for Nurofen",
        priceHistory: [34, 26, 18]
    },
    {
        id: 3,
        name: "Aspirina",
        producer: "Pool pharma",
        description: "This is a description for Aspirina",
        priceHistory: [23, 16, 25, 20]
    },
    {
        id: 4,
        name: "Ceapa ta",
        producer: "Bonduelle",
        description: "This is a description for ceapa bonduelle",
        priceHistory: [1, 2, 2, 3, 2]
    },
];

export async function update(item){
    let response = await AsyncStorage.getItem('medicineList'); 
    let medicineList = await JSON.parse(response);

    let medicine = medicineList.find(m => m.id === item.id);

    medicine.name = item.name;
    medicine.producer = item.producer;
    medicine.description = item.description;
    medicine.priceHistory = item.priceHistory;

    await AsyncStorage.setItem('medicineList', JSON.stringify(medicineList));

    response = await AsyncStorage.getItem('medicineList'); 
    medicineList = await JSON.parse(response);
}

export function getList(){
    return medicineListSeed;
}

async function getLastId(){
    let response = await AsyncStorage.getItem('medicineList'); 
    let medicineList = await JSON.parse(response);
    let max = 0;

    medicineList.forEach(m => {
        if (m.id > max){
            max = m.id;
        }
    });

    return max;
}

export async function add(item){
    item.id = getLastId()+1;

    let response = await AsyncStorage.getItem('medicineList'); 
    let medicineList = await JSON.parse(response);

    medicineList.push(item);

    await AsyncStorage.setItem('medicineList', JSON.stringify(medicineList));
}

export async function deleteMedicine(itemId){
    let response = await AsyncStorage.getItem('medicineList'); 
    let medicineList = await JSON.parse(response);

    let medicine = medicineList.find(m => m.id === itemId);
    let index = medicineList.indexOf(medicine);


    if (index > -1) {
        medicineList.splice(index, 1);
    }

    await AsyncStorage.setItem('medicineList', JSON.stringify(medicineList));
}