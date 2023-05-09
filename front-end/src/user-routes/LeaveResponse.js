import React, { useEffect, useState } from 'react'
import { Button, Container,FormGroup,Input,Label,Modal, ModalBody, ModalFooter, ModalHeader, NavLink, Pagination, PaginationItem, PaginationLink, Table } from 'reactstrap'
import { loadAllEmp } from '../services/user-service'
import {toast} from 'react-toastify';
import { Link, NavLink as ReactLink, useNavigate} from 'react-router-dom';

import { Base } from '../components/Base';
import { leaveResponse, loadPendingRequest } from '../services/leave-service';


 const LeaveResponse = () => {
  const [list,setList]=useState(null)
  const [leave, setLeave] = useState({
    applicationId:'',
    employeeId: '',
    leaveType:'',
    message:'',
    leaveFrom:'',
    leaveTill:'',
    remarks:'',
    leaveStatus:''
})
const setLeaveState = (emp) => {
  setLeave({
    applicationId:emp.applicationId,
    employeeId: emp.employeeId,
    leaveType: emp.leaveType,
    message: emp.message,
    leaveFrom: emp.leaveFrom,
    leaveTill: emp.leaveTill,
    applicationDate:emp.applicationDate,
    remarks: '',
    leaveStatus:emp.leaveStatus
  });
};
  useEffect(() => {
    loadPendingRequest().then(data=>{
      console.log(data)
        setList(data)
    }).catch(error=>{
      toast.error("Error loading page")
    })
  }, [])
  const printDate=(numbers)=>{
    return new Date(numbers).toLocaleDateString()
  }
  const [modal, setModal] = useState(false);
  const togglePopup = () => setModal(!modal);

  const submitResponse = (e, status) => {
    e.preventDefault();
    setLeave({ ...leave, leaveStatus: status });
    togglePopup();
    console.log(leave);
    leaveResponse({ ...leave, leaveStatus: status }).then(data=>{
    console.log(data)
      setList(data)
  }).catch(error=>{
    toast.error("Error loading page")
  })
  };
  useEffect(() => {
    console.log(leave); // add this line
  }, [leave]);
  
const handleChange=(event,property)=>{
  setLeave({...leave,[property]:event.target.value})
}
  return (
    <>
    <Modal isOpen={modal} toggle={togglePopup}>
        <ModalHeader toggle={togglePopup}>Provide Your Remarks</ModalHeader>
        <ModalBody>
        <FormGroup>
          <Label for="reamrks">Comments</Label>
          <Input type='textarea' id='remarks' value={leave.remarks} onChange={(e)=>handleChange(e,'remarks')}/>
        </FormGroup>
        </ModalBody>
        <ModalFooter>
          <button color="primary" onClick={(e)=>submitResponse(e,1)}>Approve</button>
          <button color="secondary" onlick={(e)=>submitResponse(e,2)}>Reject</button>
        </ModalFooter>
      </Modal>
    <Base>

    <div className="container">

    <div className="container-fluid">
      
      <Table striped>
  <thead>
    <tr>
      <th>
        Application Id
      </th>
      <th>
      Employee Id
      </th>
      <th>
      Leave Type
      </th>
      <th>
      Start Date
      </th>
      <th>
      End Date
      </th>
      <th>
       Comments
      </th>
      <th>
      More
      </th>
    </tr>
  </thead>
  <tbody>
  {list && Array.isArray(list) && (
  list.map((emp) => {
    return (
      <tr key={emp.applicationId}>
        <td>{emp.applicationId}</td>
        <td>{emp.employeeId}</td>
        <td>{emp.leaveType}</td>
        <td>{printDate(emp.leaveFrom)}</td>
        <td>{printDate(emp.leaveTill)}</td>
        <td>{emp.message}</td>
        <td>
          <Button
            className="btn btn-secondary border-0"
            onClick={() => {
              setLeaveState(emp);
              togglePopup();
            }}
          >
            Respond
          </Button>
        </td>
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
export default LeaveResponse;
