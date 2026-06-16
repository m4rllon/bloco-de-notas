import EvilIcons from "@react-native-vector-icons/evil-icons";
import { TypeInput } from "../../../interfaces/Input";
import { Container, CustomInput, InputContainer, Label } from "./styles";
import { theme } from "../../../theme";
import { Text, TextInputProps, TouchableOpacity } from "react-native";
import { useState } from "react";
import FontAwesomeFreeSolid from "@react-native-vector-icons/fontawesome-free-solid";
import { Control, Controller, FieldValues, Path } from "react-hook-form";

interface InputProps<TFieldValues extends FieldValues> extends TextInputProps {
    control: Control<TFieldValues>;
    name: Path<TFieldValues>; 
    error?: string;
    type: TypeInput;
    textLabel: string;
    placeholder: string;
}

export function Input<TFieldValues extends FieldValues>({control, name, error, type, textLabel, placeholder}:InputProps<TFieldValues>){
    const [secureTextEntryState, setSecureTextEntryState] = useState(type === TypeInput.PASSWORD);

    return <Container>
        <Label>{textLabel}</Label>
            <Controller 
            control={control}
            render={ ({ field: {onChange, value} }) => (
                <InputContainer>
                    <CustomInput 
                    placeholder={placeholder}
                    onChangeText={onChange}
                    value={value}/>
                    {type === TypeInput.EMAIL ? <EvilIcons 
                    name="envelope" 
                    size={30}
                    color={theme.colors.inkSoft}/>:<></>}

                    {type === TypeInput.PASSWORD ? 
                    <TouchableOpacity
                    onPress={() => setSecureTextEntryState(prev => !prev)}>
                        <FontAwesomeFreeSolid
                        name={secureTextEntryState ? "eye" : "eye-slash"}
                        size={20}
                        color={theme.colors.inkSoft}/>
                    </TouchableOpacity>:<></>}
                </InputContainer>    
            )}
            name={name}/>
            { error && <Text>{ error }</Text>}
    </Container>    
}