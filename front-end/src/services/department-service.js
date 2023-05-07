import { myAxios, privateAxios } from "./helper";
 const loadcategories=()=>{
    return myAxios.get('/department/').then(Response=>{return Response.data})
};

export const addDepartment=(dept)=>{
<<<<<<< HEAD
    return privateAxios.post(`/department/`,dept).then((Response)=>Response.data);
};

export const getDepartment=(id)=>{
    return privateAxios.get(`/department/${id}`).then((Response)=>Response.data);
};

=======
    console.log(dept)
    return privateAxios.post(`/department/`,dept).then((Response)=>Response.data);
};

>>>>>>> 06c6db59a885ec203916cddb2e61d4d33c4bca4e
export default loadcategories;
