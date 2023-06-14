import React from 'react';
import SideBar from "@/components/admin/sideBar";
import Grid from "@mui/material/Grid";

function MyPage(props) {
    return (
        <Grid container spacing={2} mt={4} md={4} p={10}>
            <Grid xs={8}>
                <SideBar></SideBar>
            </Grid>
        </Grid>

    );
}

export default MyPage;