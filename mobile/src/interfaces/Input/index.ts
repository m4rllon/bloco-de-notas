import { TextInputProps } from "react-native";

export enum TypeInput {
    EMAIL,
    PASSWORD,
    OTHER,
}

export interface InputProps extends TextInputProps {
    type: TypeInput;
    textLabel: string;
    placeholder: string;
}