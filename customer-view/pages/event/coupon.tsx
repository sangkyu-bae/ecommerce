import React from 'react';
import {StyledContainer} from "@/components/common/GridComponent";
import {useQuery} from "@tanstack/react-query";
import CardContent from "@mui/material/CardContent";
import {CardActions, Typography} from "@mui/material";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";

function Coupon(props) {

    return (
       <StyledContainer>
           <Box sx = {{width:"100%"}}>
               <Box sx={{ minWidth: 500,maxWidth : '50%', margin:'2em auto', flex:1, flexDirection: 'column'}}>
                   <Card variant="outlined">
                       <React.Fragment>
                           <CardContent sx={{padding:'3%', textAlign:'center'}}>
                               <Typography variant="h5" component="div">
                                   쿠폰발행
                               </Typography>

                               <Typography variant="body2">
                                    test 쿠폰을 발행해 보아요~!
                               </Typography>
                           </CardContent>
                           <CardActions>
                               <Button size="small">Learn More</Button>
                           </CardActions>
                       </React.Fragment>
                   </Card>
               </Box>
               <Box sx={{ minWidth: 500,maxWidth : '50%', margin:'2em auto', flex:1, flexDirection: 'column'}}>
                   <Card variant="outlined">
                       <React.Fragment>
                           <CardContent>
                               <Typography gutterBottom sx={{ color: 'text.secondary', fontSize: 14 }}>
                                   Word of the Day
                               </Typography>
                               <Typography variant="h5" component="div">
                                   test
                               </Typography>
                               <Typography sx={{ color: 'text.secondary', mb: 1.5 }}>adjective</Typography>
                               <Typography variant="body2">
                                   well meaning and kindly.
                                   <br />
                                   {'"a benevolent smile"'}
                               </Typography>
                           </CardContent>
                           <CardActions>
                               <Button size="small">Learn More</Button>
                           </CardActions>
                       </React.Fragment>
                   </Card>
               </Box>
               <Box sx={{ minWidth: 500,maxWidth : '50%', margin:'2em auto', flex:1, flexDirection: 'column'}}>
                   <Card variant="outlined">
                       <React.Fragment>
                           <CardContent>
                               <Typography gutterBottom sx={{ color: 'text.secondary', fontSize: 14 }}>
                                   Word of the Day
                               </Typography>
                               <Typography variant="h5" component="div">
                                   test
                               </Typography>
                               <Typography sx={{ color: 'text.secondary', mb: 1.5 }}>adjective</Typography>
                               <Typography variant="body2">
                                   well meaning and kindly.
                                   <br />
                                   {'"a benevolent smile"'}
                               </Typography>
                           </CardContent>
                           <CardActions>
                               <Button size="small">Learn More</Button>
                           </CardActions>
                       </React.Fragment>
                   </Card>
               </Box>
           </Box>


       </StyledContainer>


    );
}

export default Coupon;
