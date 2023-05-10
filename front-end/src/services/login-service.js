import { myAxios } from "./helper";
 const login=(loginDetail)=>{
    return myAxios.post('/auth/login',loginDetail).then((Response)=>Response.data)
};

export default login;
