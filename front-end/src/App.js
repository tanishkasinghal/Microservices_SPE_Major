import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Base } from './components/Base';
import {BrowserRouter,Routes,Route} from 'react-router-dom';

// import {
//   createBrowserRouter,
//   createRoutesFromElements,
//   Route,
//   RouterProvider,
// } from "react-router-dom";
import  Home  from './pages/Home';
import Login from './pages/Login';
import  AddEmployee  from './pages/AddEmployee';
import AddDepartment from './pages/AddDepartment.js';
import  About  from './pages/About';
import EmployeeDashboard from './user-routes/EmployeeDashboard';
import PrivateRoute from './components/PrivateRoute';
import ProfileByAdmin from './user-routes/ProfileByAdmin';
import UserProvider from './context/UserProvider';

import Profile from './user-routes/ProfileSelf';
import UpdateDetail from './user-routes/UpdateDetail';
import LeaveRequest from './pages/LeaveRequest';
import EmployeeList from './components/EmployeeList';
import LeaveResponse from './user-routes/LeaveResponse';
import LeaveStatus from './user-routes/LeaveStatus';



function App() {
  return (
    <UserProvider>
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<Login/>}/>
      <Route path="/login" element={<Login/>}/>
      <Route path="/employee" element={<PrivateRoute/>}>
        <Route path="dashboard" element={<EmployeeDashboard/>}/>
        <Route path="employeeList" element={<EmployeeList/>}/>
        <Route path="apply" element={<LeaveRequest/>}/>
        <Route path="addEmployee" element={<AddEmployee/>}/>
        <Route path="addDepartment" element={<AddDepartment/>}/>
        <Route path="leaveResponse" element={<LeaveResponse/>}/>
        <Route path="view" element={<LeaveStatus/>}/>
        <Route path="about" element={<About/>}/>
        <Route path="profile-info/:id" element={<ProfileByAdmin/>}/>
        <Route path="update-detail/:id" element={<UpdateDetail/>}/>
        <Route path="profile-info" element={<Profile/>}/>
      </Route>
    </Routes>
    </BrowserRouter>
    </UserProvider>
  );
}

export default App;
