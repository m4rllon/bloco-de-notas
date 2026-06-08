import { Container } from "../../components/Container";
import { BackButton, HeaderContainer, HeaderSubText, HeaderText } from "./styles";
import { EvilIcons } from "@react-native-vector-icons/evil-icons";
import { Input } from "../../components/Form/Input";
import { TypeInput } from "../../interfaces/Input";

export function SingUp(){
    return <Container>
        <HeaderContainer>
            <BackButton activeOpacity={0.7}>
                <EvilIcons name="chevron-left" size={30}/>
            </BackButton>
            <HeaderSubText>Leva menos de um minuto</HeaderSubText>
            <HeaderText>Criar conta</HeaderText>
        </HeaderContainer>
        <Input 
        type={TypeInput.OTHER}
        textLabel={"Nome de usuário"}
        placeholder="Como os manos das ruas te chamam?"/>
        <Input 
        type={TypeInput.EMAIL}
        textLabel={"E-mail"}
        placeholder="juninhoGameplays@yahoo.com"/>
        <Input 
        type={TypeInput.PASSWORD}
        textLabel={"Senha"}
        placeholder="Nada de usar o nome da mãe!"/>
    </Container>
}