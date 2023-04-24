import React, { useContext, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { Button, Card, CardBody, CardHeader, Col, Container, Form, FormGroup, Input, Label, Row, Toast } from 'reactstrap';
import { doLogin } from '../auth';
import { Base } from '../components/Base';
import userContext from '../context/userContext';
import login from '../services/login-service';

const Login = () => {
    const userContxtData = useContext(userContext);
    const navigate=useNavigate()
    const [loginDetail, setLoginDetail] = useState({
        username:'',
        password:''
    })

    const handleChange=(event,property)=>{
        setLoginDetail({...loginDetail,[property]:event.target.value})
        console.log(loginDetail.username)
    }

    const handleFormSubmit=(event)=>{
        event.preventDefault();
        console.log(loginDetail);

        //validation
        if(loginDetail.username.trim()=='' || loginDetail.password.trim()==''){
            alert("Both fields are required")
            return; 
        };

        //submit data to server
        login(loginDetail).then((data)=>{
            console.log(data)
            //save token to localStorage
            doLogin(data,()=>{
                console.log("Login detail is saved to localStorage");
                userContxtData.setUser({
                    data: data.employee,
                    login: true,
                  });
                //redirect to user dashboard
                navigate("/employee/dashboard")
            })
        }).catch(error=>{
            console.log(error);
        })
    };
  
    const handleReset=()=>{
        setLoginDetail({
            username:"",
            password:""
        });
    };

  return (
    <Base>
        <Container>
            <Row className='mt-4'>
                <Col sm={{size:6,offset:3}}>
                <Card>
                <CardHeader>
                    <h3>Login </h3>
                </CardHeader>
                <CardBody>
                    <Form onSubmit={handleFormSubmit}>
                        <FormGroup>
                            <Label for="username">Enter Username</Label>
                            <Input type='email' value={loginDetail.username} onChange={(e)=>handleChange(e,'username')} id='username'/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="password">Enter Password</Label>
                            <Input type='password' value={loginDetail.password} onChange={(e)=>handleChange(e,'password')} id='password'/>
                        </FormGroup>
                        <Container className='text-center'>
                            <Button type='submit' color='dark'>Login</Button>
                            <Button onClick={handleReset} color='secondary' className='ms-2' type='reset'>Reset</Button>
                        </Container>
                    </Form>
                </CardBody>
            </Card>
                </Col>
            </Row>
        </Container>
    </Base>
  )
}

export default Login;
