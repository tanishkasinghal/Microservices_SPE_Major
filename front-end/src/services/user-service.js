import { myAxios, privateAxios } from "./helper";
 export const addEmployee=(emp)=>{
    console.log(emp)
    return privateAxios.post(`/employee/register/${emp.dept_id}`,emp).then((Response)=>Response.data);
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

//export default addEmployee;

