import { myAxios } from "./helper";
 export const requestLeave=(data)=>{
    return myAxios.post('/leave/',data).then((Response)=>Response.data)
};

export const loadPendingRequest=()=>{
    return myAxios.get('/leave/pending').then((Response)=>Response.data)
};

export const loadAllRequest=(id)=>{
    return myAxios.get(`/leave/${id}`).then((Response)=>Response.data)
};

export const leaveResponse=(data)=>{
    return myAxios.post('/leave/submitResponse',data).then((Response)=>Response.data)
};

