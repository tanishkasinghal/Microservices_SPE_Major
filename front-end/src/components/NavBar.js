import React, { useContext, useEffect, useState } from 'react';
import { NavLink as ReactLink, useNavigate} from 'react-router-dom';
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
} from 'reactstrap';
import { doLogout, getCurrentUserDetail, isLoggedIn } from '../auth';
import userContext from '../context/userContext';


const NavBar = () => {
  const userContextData=useContext(userContext)
  const navigate=useNavigate()
  const [isOpen, setIsOpen] = useState(false);
  const toggle = () => setIsOpen(!isOpen);

  const [login, setLogin] = useState(false);
  const [user, setUser] = useState(undefined);
  useEffect(() => {
    setLogin(isLoggedIn())
    setUser(getCurrentUserDetail())
  }, [login])
  const logout=()=>{
    doLogout(()=>{
      setLogin(false)
      userContextData.setUser({
        data:null,
        login:false
      })
      navigate("/")
    })
  }
  return (
    <div>
        <Navbar color="dark" dark expand="md" className='px-5'>
          <NavbarBrand tag={ReactLink} to="/employee/dashboard">Employee Management</NavbarBrand>
          <NavbarToggler onClick={toggle} />
          <Collapse isOpen={isOpen} navbar>
            <Nav className="me-auto" navbar>
              {/* <NavItem>
                <NavLink tag={ReactLink} to="/home">Home</NavLink>
              </NavItem>
              <NavItem>
                <NavLink tag={ReactLink} to="/about">About</NavLink>
              </NavItem> */}
              {   
                 (user) && (user.roles[0].id==501) &&  (
                    <>
                <NavItem>
                <NavLink tag={ReactLink} to="/employee/addEmployee">Add Employee</NavLink>
              </NavItem>
              <NavItem>
                <NavLink tag={ReactLink} to="/employee/addDepartment">Add Department</NavLink>
              </NavItem>
                    </>
                  )
              }
            
            
              {/* <UncontrolledDropdown nav inNavbar>
                <DropdownToggle nav caret>
                  More
                </DropdownToggle>
                <DropdownMenu right>
                  <DropdownItem>
                    Contact
                  </DropdownItem>
                  <DropdownItem divider />
                  <DropdownItem>
                    Services
                  </DropdownItem>
                </DropdownMenu>
              </UncontrolledDropdown> */}
            </Nav>
            <Nav navbar>
              {
                login && (
                  <>
                     <NavItem onClick={logout}>
                <NavLink tag={ReactLink} to="/login">Logout</NavLink>
              </NavItem>
              <NavItem>
                <NavLink tag={ReactLink} to="/employee/profile-info">{user.firstName}</NavLink>
              </NavItem>
                  </>
                 
                )
              }
            {
              !login && (
                <NavItem>
                <NavLink tag={ReactLink} to="/login">Login</NavLink>
              </NavItem>
              )
            }
            </Nav>
          </Collapse>
        </Navbar>
      </div>
  )
}

export default NavBar
