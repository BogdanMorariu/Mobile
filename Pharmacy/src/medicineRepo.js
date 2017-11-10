/**
 * Created by Bogdan on 10.11.2017.
 */
let medicineList = [
    {
        id: 1,
        name: "Vitamina C",
        producer: "ProfiPharm",
        description: "This is a description for Vitamin C"
    },
    {
        id: 2,
        name: "Nurofen",
        producer: "GSK",
        description: "This is a description for Nurofen"
    },
    {
        id: 3,
        name: "Aspirina",
        producer: "Pool pharma",
        description: "This is a description for Aspirina"
    },
    {
        id: 4,
        name: "Ceapa",
        producer: "Bonduelle",
        description: "This is a description for ceapa bonduelle"
    },
];

export function update(item){
    let medicine = medicineList.find( m => m.id === item.id);
    medicine.name = item.name;
    medicine.producer = item.producer;
    medicine.description = item.description;
}

export function getList(){
    return medicineList;
}