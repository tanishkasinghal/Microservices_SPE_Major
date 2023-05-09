import React, { useContext, useEffect, useState } from 'react'
import { Button, Container,FormGroup,Input,Label,Modal, ModalBody, ModalFooter, ModalHeader, NavLink, Pagination, PaginationItem, PaginationLink, Table } from 'reactstrap'
import { loadAllEmp } from '../services/user-service'
import {toast} from 'react-toastify';
import { Link, NavLink as ReactLink, useNavigate} from 'react-router-dom';

import { Base } from '../components/Base';
import { leaveResponse, loadAllRequest, loadPendingRequest } from '../services/leave-service';
import userContext from '../context/userContext';


 const LeaveStatus = () => {
    const user=useContext(userContext)
  const [list,setList]=useState([])
  useEffect(() => {
    const fetchData = async () => {
        try {
            const data = await loadAllRequest(user.user.data.id)
            setList(data)
        } catch(error) {
            toast.error("Error loading page")
        }
    }

    fetchData()
}, [user.user.data.id])
  const printDate=(numbers)=>{
    return new Date(numbers).toLocaleDateString()
  }
  const getStatusText = (status) => {
    switch(status) {
      case 0:
        return 'Pending'
      case 1:
        return 'Approved'
      case 2:
        return 'Rejected'
      default:
        return ''
    }
  }
  return (
    <>
    <Base>

    <div className="container">

    <div className="container-fluid">
      
      <Table striped>
  <thead>
    <tr>
      <th>Application Id</th>
      <th>Leave Type</th>
      <th>Start Date</th>
      <th>End Date</th>
      <th>Comments</th>
      <th>Remarks</th>
      <th>Status</th>
    </tr>
  </thead>
  <tbody>
  {console.log(list)}
  {list && Array.isArray(list) && (
  list.map((emp) => {
    return (
      <tr key={emp.applicationId}>
        <td>{emp.applicationId}</td>
        <td>{emp.leaveType}</td>
        <td>{printDate(emp.leaveFrom)}</td>
        <td>{printDate(emp.leaveTill)}</td>
        <td>{emp.message}</td>
        <td>{emp.remarks}</td>
        <td>{getStatusText(emp.leaveStatus)}</td>
      </tr>
    );
  })
) }

</tbody>

</Table>

<Container className='text-center mt-3'>

</Container>
    </div>
    </div>
    </Base>
</>
  )
}
export default LeaveStatus;
