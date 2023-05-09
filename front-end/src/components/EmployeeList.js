import React, { useEffect, useState } from 'react'
import { Button, Container, NavLink, Pagination, PaginationItem, PaginationLink, Table } from 'reactstrap'
import { loadAllEmp } from '../services/user-service'
import {toast} from 'react-toastify';
import { Link, NavLink as ReactLink, useNavigate} from 'react-router-dom';

import { Base } from './Base';


 const EmployeeList = () => {
  const [empList,setEmpList]=useState({
    content:[],
    totalPages:'',
    totalElements:'',
    pageSize:'',
    lastPage:false,
    pageNumber:''

  })
  useEffect(() => {
    // loadAllEmp(0,5).then((data)=>{
      
    // console.log(data);
    //   setEmpList(data);
    // }).catch(error=>{
    //   console.log(error);
    // })
    changePage(0)
  }, [])

  const changePage=(pageNumber=0,pageSize=5)=>{
    // if(empList.lastPage){
    //   return
    // }
    if(pageNumber>empList.pageNumber && empList.lastPage){
      return
    }
    if(pageNumber<empList.pageNumber && empList.pageNumber==0){
      return
    }
    loadAllEmp(pageNumber,pageSize).then(data=>{
      setEmpList(data)
      window.scroll(0,0)
    }).catch(error=>{
      toast.error("Error loading page")
    })
  }
  
  return (

    <Base>
    <div className="container">

    <div className="container-fluid">
      <h2>Total Employees {empList.totalElements-1}</h2>
      <Table striped>
  <thead>
    <tr>
      <th>
        Employee Id
      </th>
      <th>
      Employee First Name
      </th>
      <th>
      Employee Last Name
      </th>
      <th>
      Employee EmailId
      </th>
      <th>
      More
      </th>
    </tr>
  </thead>
  <tbody>
      {
        
        empList.content.map((emp)=>{
            return(
              (emp.roles[0].id==502) && (
              <tr key={emp.id}>
                <td>{emp.id}</td>
                <td>{emp.firstName}</td>
                <td>{emp.lastName}</td>
                <td>{emp.emailId}</td>
                <td><Link className='btn btn-secondary border-0' to={`/employee/profile-info/`+emp.id}>View Profile</Link></td>
                {/* <td>{emp.department.deptName}</td> */}
              </tr>
              )

            )
        })
      }

  </tbody>
</Table>

<Container className='text-center mt-3'>
<Pagination>
  <PaginationItem onClick={()=>changePage(empList.pageNumber-1)} disabled={empList.pageNumber==0}>
    <PaginationLink previous >

    </PaginationLink>
  </PaginationItem>

      {
        [...Array(empList.totalPages  )].map((item,index)=>(
          
            <PaginationItem onClick={()=>changePage(index)} active={index===empList.pageNumber} key={index}> 
              <PaginationLink>
              {index+1}
              </PaginationLink>
            </PaginationItem>
        ))
      }
  <PaginationItem onClick={()=>changePage(1+empList.pageNumber)} disabled={empList.lastPage}>
    <PaginationLink next>

    </PaginationLink>
  </PaginationItem>
  </Pagination>
</Container>
    </div>
    </div>
    </Base>

  )
}
export default EmployeeList;
