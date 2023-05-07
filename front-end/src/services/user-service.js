import { myAxios, privateAxios } from "./helper";
 export const addEmployee=(emp)=>{
    console.log(emp)
    return privateAxios.post(`/employee/register`,emp).then((Response)=>Response.data);
};

export const loadAllEmp=(pageNumber,pageSize)=>{
    return privateAxios.get(`/employee/?pageNumber=${pageNumber}&pageSize=${pageSize}`).then(Response=>Response.data);
};

export const loadEmp=(id)=>{
    return privateAxios.get(`/employee/`+id).then(Response=>Response.data)
}

export const deleteEmp=(emp)=>{
    return privateAxios.delete(`/employee/${emp.id}`).then(Response=>Response.data)
}
export const updateDetail=(emp)=>{
<<<<<<< HEAD
    return privateAxios.put(`/employee/${emp.id}`,emp).then(Response=>Response.data)
=======
    return privateAxios.put(`/employee/${emp.id}`).then(Response=>Response.data)
>>>>>>> 06c6db59a885ec203916cddb2e61d4d33c4bca4e
}

//export default addEmployee;

