import { Keyboard, KeyboardAvoidingView, Platform, ScrollView, Text, TouchableWithoutFeedback } from "react-native";
import { CustomButton } from "../../../../components/CustomButton";
import { Input } from "../../../../components/Form/Input";
import { Info } from "../../../../components/Info";
import { TypeInput } from "../../../../interfaces/Input";
import { CtaLoginText, FormContainer, LoginText } from "./styles";
import * as yup from "yup"
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";

interface FormData {
    username: string;
    email: string;
    password: string;
    repeatPassword: string;
}

const schema = yup.object({
    username: yup.string()
        .required('Informe seu nome de usuário.'),
    email: yup.string()
        .email('Digite um e-mail válido!').required('Informe seu email.'),
    password: yup.string()
        .required('Informe sua senha.'),
    repeatPassword: yup.string()
        .required('Informe sua senha novamente.')
        .oneOf([yup.ref('password')], "As duas senhas devem ser iguais doido!")
})

export function SingUpForm(){
    const [categoryErrorMessage, setCategoryErrorMessage] = useState<string | undefined>()

    const {
        control,
        handleSubmit,
        formState: { errors } } = useForm<FormData>({
            resolver: yupResolver(schema)
        })

    const submitForm = (form:FormData) => {
        console.log(form)
    }

    useEffect(() => {
        console.log(categoryErrorMessage)
    }, [categoryErrorMessage])

    return <KeyboardAvoidingView
    behavior={Platform.OS == 'ios' ? 'padding' : 'height'}
    style={{ flex: 1 }}>
        <TouchableWithoutFeedback onPress={Keyboard.dismiss}>
            <FormContainer>
                <ScrollView>
                    <Input 
                    control={control}
                    name="username"
                    error={errors.username?.message}
                    type={TypeInput.OTHER}
                    textLabel={"Nome de usuário"}
                    placeholder="Como os manos das ruas te chamam?"/>
                    <Input 
                    control={control}
                    name="email"
                    error={errors.email?.message}
                    type={TypeInput.EMAIL}
                    textLabel={"E-mail"}
                    placeholder="juninhoGameplays@yahoo.com"/>
                    <Input 
                    control={control}
                    name="password"
                    error={errors.password?.message}
                    type={TypeInput.PASSWORD}
                    textLabel={"Senha"}
                    placeholder="Nada de usar o nome da mãe!"/>
                    <Input 
                    control={control}
                    name="repeatPassword"
                    error={errors.repeatPassword?.message}
                    type={TypeInput.PASSWORD}
                    textLabel={"Repetir senha"}
                    placeholder="Tem que colocar igual a de cima viu!"/>
                    <Info type="INFORM" 
                    icon="🔒"
                    content="Suas notas locais serão migradas para a sua conta automaticamente."/>

                    {categoryErrorMessage && <Text>{categoryErrorMessage}</Text>}

                    <CustomButton 
                    type={"primary"} 
                    activeOpacity={0.7}
                    onPress={handleSubmit(submitForm)}>
                        Criar conta
                    </CustomButton>
                    <LoginText>
                        Já Possui conta?{" "} <CtaLoginText onPress={() => {}}>Entrar</CtaLoginText>.
                    </LoginText>
                </ScrollView>
            </FormContainer>
        </TouchableWithoutFeedback>
    </KeyboardAvoidingView>
     
}