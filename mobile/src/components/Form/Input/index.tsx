import EvilIcons from "@react-native-vector-icons/evil-icons";
import { InputProps, TypeInput } from "../../../interfaces/Input";
import { Container, CustomInput, InputContainer, Label } from "./styles";
import { theme } from "../../../theme";
import { TouchableOpacity } from "react-native";
import { useState } from "react";

export function Input({type, textLabel, placeholder}:InputProps){
    const [secureTextEntryState, setSecureTextEntryState] = useState(type === TypeInput.PASSWORD);

    return <Container>
        <Label>{textLabel}</Label>
        <InputContainer>
            <CustomInput placeholder={placeholder}/>

            {type === TypeInput.EMAIL ? <EvilIcons 
            name="envelope" 
            size={30}
            color={theme.colors.inkSoft}/>:<></>}

            {type === TypeInput.PASSWORD ? 
            <TouchableOpacity
            onPress={() => setSecureTextEntryState(prev => !prev)}>
                <EvilIcons 
                name={secureTextEntryState ? "eye" : "eye"} 
                size={30}
                color={theme.colors.inkSoft}/>
            </TouchableOpacity>:<></>}
        </InputContainer>
    </Container>    
}