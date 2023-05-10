import React, { useContext } from 'react'
import { Base } from '../components/Base'
import {requestLeave} from '../services/leave-service';
import loadcategories from '../services/department-service.js';
import { getCurrentUserDetail } from '../auth';
import { useEffect, useState } from 'react';
import userContext from '../context/userContext';
import { Button, Card, CardBody, CardHeader, Col, Container, Form, FormGroup, Input, Label, Row } from 'reactstrap';
//import  LeaveType  from '../utils/utility';

 const LeaveRequest = () => {
    const LeaveType = ["Sick Leave","Planned Leave","Maternity or Paternity Leave","Bereavement Leave"];
    const user=useContext(userContext)
    console.log(user)
    const [data, setData] = useState({
        employeeId: user.user.data.id,
        leaveType:'',
        message:'',
        leaveFrom:new Date(),
        leaveTill:''
    })


    const handleChange = (event, property) => {
        const value = event.target.value;
      
        // Check if the selected date is before today's date
        if (property === 'leaveFrom' || property === 'leaveTill') {
          const now = new Date();
          const selectedDate = new Date(value);
          if (selectedDate < now) {
            alert('Selected date cannot be before today');
            return;
          }
        }
      
        setData({ ...data, [property]: value });
      
        // Check for start date and end date validation
        if (property === 'leaveFrom') {
          const startDate = new Date(value);
          const endDate = new Date(data.leaveTill);
          if (endDate && startDate > endDate) {
            alert('Start date cannot be after end date');
            setData({ ...data, leaveFrom: '' });
          }
        } else if (property === 'leaveTill') {
          const startDate = new Date(data.leaveFrom);
          const endDate = new Date(value);
          if (startDate && endDate < startDate) {
            alert('End date cannot be before start date');
            setData({ ...data, leaveTill: '' });
          }
        }
      };
      
      
      
      


    const submitForm=(e)=>{
        e.preventDefault();
    
        if(data.leaveType.trim()===''){
            alert("leaveType is required")
            return;
        }
        if(data.message.trim()===''){
            alert("Comments are required")
            return;
        }
        if(data.leaveFrom.trim()===''){
            alert("start date is required")
            return;
        }
        if(data.leaveTill===''){
            alert("End date is required")
            return;
        }

        console.log("final")
        console.log(data)
        requestLeave(data).then((resp)=>{
            alert("Requested")
            setData({
                employeeId: user.user.data.id,
        leaveType:'',
        message:'',
        leaveFrom:new Date(),
        leaveTill:''
            })
    
        }).catch((error)=>{
            alert("Error")
    
        })
    }
  return (
    <Base>
        <Container>
            <Row className='mt-4'>

                {/* {JSON.stringify(data)} */}
                <Col sm={{size:6,offset:3}}>
                <Card>
                <CardHeader>
                    <h3>Request Leave</h3>
                </CardHeader>
                <CardBody>
                    {/* {JSON.stringify(data)} */}   
                    <Form onSubmit={submitForm}>
                        <FormGroup>
                            <Label for="leaveType">Leave Type</Label>
                            <Input id="leaveType" defaultValue={0} name="leaveType" type="select" onChange={(e)=>handleChange(e,'leaveType')}>
                                <option disabled value={0}>-- Leave Type --</option>
                            {
                                LeaveType.map((s,id)=>(
                                    <>
                
                                    <option value={s} key={id}>{s}</option>
                                    </>
                             
                                ))
                            }
                            </Input> 
                        </FormGroup>
                        <FormGroup>
                            <Label for="message">Comments</Label>
                            <Input type='textarea' id='message' value={data.message} onChange={(e)=>handleChange(e,'message')}/>
                        </FormGroup>
                       
                        <FormGroup>
                            <Label for="leaveFrom">Start Date</Label>
                            <Input type='date' id='leaveFrom' value={data.leaveFrom} onChange={(e)=>handleChange(e,'leaveFrom')}/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="leaveFrom">End Date</Label>
                            <Input type='date' id='leaveTill' value={data.leaveTill} onChange={(e)=>handleChange(e,'leaveTill')}/>
                        </FormGroup>
                      
                        <Container className='text-center'>
                            <Button type='submit' color='dark'>Request</Button>
                        </Container>
                    </Form>
                </CardBody>
            </Card>
                </Col>
            </Row>
        </Container>
    </Base>
  );
};
export default LeaveRequest;
