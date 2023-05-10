import { myAxios, privateAxios } from "./helper";
 const loadcategories=()=>{
    return myAxios.get('/department/').then(Response=>{return Response.data})
};

export const addDepartment=(dept)=>{
    return privateAxios.post(`/department/`,dept).then((Response)=>Response.data);
};

export const getDepartment=(id)=>{
    return privateAxios.get(`/department/${id}`).then((Response)=>Response.data);
};


export default loadcategories;
