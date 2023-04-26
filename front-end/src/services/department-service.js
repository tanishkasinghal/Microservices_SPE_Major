import { myAxios, privateAxios } from "./helper";
 const loadcategories=()=>{
    return myAxios.get('/department/').then(Response=>{return Response.data})
};

export const addDepartment=(dept)=>{
    console.log(dept)
    return privateAxios.post(`/department/`,dept).then((Response)=>Response.data);
};

export default loadcategories;
