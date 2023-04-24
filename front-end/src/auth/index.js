//isLoggedIn => agr local storage me token hai to logged in hai
export const isLoggedIn=()=>{
    let data=localStorage.getItem("data")
    if(data==null){
        return false;
    }
    else{
        return true;
    }
};
//doLogin =>data => set to localstorage
export const doLogin=(data,next)=>{
    localStorage.setItem("data",JSON.stringify(data));
    //redirect to user dashboard
    
    next()
};


//do Logout=> remove from localstorage
export const doLogout=(next)=>{
    localStorage.removeItem("data");
    next()
}
//get currentUser
export const getCurrentUserDetail=()=>{
    if(isLoggedIn()){
        return JSON.parse(localStorage.getItem("data")).employee;
    }else{
        return undefined;
    }
}

export const getToken=()=>{
    if(isLoggedIn()){
        return JSON.parse(localStorage.getItem("data")).token;
    }else{
        return null;
    }
}