import React from 'react';
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid";
import Link from "@mui/material/Link";
import {useForm} from "react-hook-form";
import Validation from "@/components/common/Validation";
import {useMutation} from "react-query";
import MemberApi from "@/api/MemberApi";
import {parse} from "postcss";


function SignInSection(props) {
    const {register, handleSubmit, formState: {errors} }=useForm<SignInFormData>();
    const onSubmit = (loginData : SignInFormData) => {
        signInMutation.mutate(loginData)
    };
    const signInMutation = useMutation(MemberApi.signIn, {
        onMutate: variable => {
            console.log("onMutate", variable);
        },
        onError: (error, variable, context) => {
            // error
            console.log(error)
        },
        onSuccess: (data, variables, context) => {
            console.log("success", data, variables, context);
        },
        onSettled: () => {
            console.log("end");
        }
    });
    const validation= Validation;
    return (
        <Box component="form" onSubmit={handleSubmit(onSubmit)} noValidate sx={{ mt: 1 }}>
            <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="이메일"
                name="email"
                autoComplete="email"
                {...register("email", {
                    ...validation.email
                })}
                error={Boolean(errors.email)}
                helperText={errors.email?.message}
                autoFocus
            />
            <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="비밀번호 (숫자+영문자+특수문자 8자리 이상)"
                type="password"
                id="password"
                autoComplete="current-password"
                {...register("password", {
                    ...validation.password
                })}
                error={Boolean(errors.password)}
                helperText={errors.password?.message}
            />
            <FormControlLabel
                control={<Checkbox value="remember" color="primary" />}
                label="Remember me"
            />
            <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
            >
                Sign In
            </Button>
            <Grid container>
                <Grid item xs>
                    <Link href="@/pages/signIn#" variant="body2">
                        Forgot password?
                    </Link>
                </Grid>
                <Grid item>
                    <Link href="@/pages/signIn#" variant="body2">
                        {"Don't have an account? Sign Up"}
                    </Link>
                </Grid>
            </Grid>
        </Box>
    );
}

export default SignInSection;